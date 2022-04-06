package com.example.mytest.common.utils;


import com.example.mytest.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Author : ellios
 * Date : 2021/1/20
 */
public class GroupCodeUtils {

    /**
     * 这里offset加了个足够大的数到10亿，保证各中code是唯一的，从而能根据code唯一得到id
     * 分组id和直播间id，不能超过20亿，否则各种code会存在冲突，不再是唯一的了
     */
    private static final long USER_CODE_OFFSET = 0;
    private static final long ADMIN_CODE_OFFSET = 4000000000L;
    private static final long ASSIST_CODE_OFFSET = 2000000000L;

    /**
     * 随机字符串
     */
    private static final char[] CHARS = new char[]{'F', 'L', 'G', 'W', '5', 'X', 'C', '3',
            'Q', 'P', 'U', '7', '6', 'Y', 'R', 'T', '2', 'H', 'S', '8', 'D', 'V', 'E', 'J', '4', 'K',
            '9', 'Z', 'M', 'A', 'N', 'B'};

    private final static int CHARS_LENGTH = 32;
    /**
     * 邀请码长度
     */
    private final static int CODE_LENGTH = 8;

    /**
     * 随机数据
     */
    private final static long SLAT = 1234561L;

    /**
     * PRIME1 与 CHARS 的长度 L互质，可保证 ( id * PRIME1) % L 在 [0,L)上均匀分布
     */
    private final static int PRIME1 = 3;

    /**
     * PRIME2 与 CODE_LENGTH 互质，可保证 ( index * PRIME2) % CODE_LENGTH  在 [0，CODE_LENGTH）上均匀分布
     */
    private final static int PRIME2 = 11;

    /**
     * 生成邀请码, 默认为 8位邀请码
     *
     * @param id 唯一的id. 支持的最大值为: (32^7 - {@link #SLAT})/{@link #PRIME1} = 11452834602
     * @return
     */
    private static String gen(Long id) {
        return gen(id, CODE_LENGTH);
    }

    /**
     * 生成邀请码
     *
     * @param id 唯一的id主键. 支持的最大值为: (32^7 - {@link #SLAT})/{@link #PRIME1}
     * @return code
     */
    private static String gen(Long id, int length) {

        // 补位
        id = id * PRIME1 + SLAT;
        //将 id 转换成32进制的值
        long[] b = new long[CODE_LENGTH];
        // 32进制数
        b[0] = id;
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            b[i + 1] = b[i] / CHARS_LENGTH;
            // 按位扩散
            b[i] = (b[i] + i * b[0]) % CHARS_LENGTH;
        }

        long tmp = 0;
        for (int i = 0; i < length - 2; i++) {
            tmp += b[i];
        }
        b[length - 1] = tmp * PRIME1 % CHARS_LENGTH;

        // 进行混淆
        long[] codeIndexArray = new long[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeIndexArray[i] = b[i * PRIME2 % CODE_LENGTH];
        }

        StringBuilder buffer = new StringBuilder();
        Arrays.stream(codeIndexArray).boxed().map(Long::intValue).map(t -> CHARS[t]).forEach(buffer::append);
        return buffer.toString();
    }

    /**
     * 将邀请码解密成原来的id
     *
     * @param code 邀请码
     * @return id
     */
    private static Long decode(String code) {
        if (code.length() != CODE_LENGTH) {
            return null;
        }
        // 将字符还原成对应数字
        long[] a = new long[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            char c = code.charAt(i);
            int index = findIndex(c);
            if (index == -1) {
                // 异常字符串
                return null;
            }
            a[i * PRIME2 % CODE_LENGTH] = index;
        }

        long[] b = new long[CODE_LENGTH];
        for (int i = CODE_LENGTH - 2; i >= 0; i--) {
            b[i] = (a[i] - a[0] * i + CHARS_LENGTH * i) % CHARS_LENGTH;
        }

        long res = 0;
        for (int i = CODE_LENGTH - 2; i >= 0; i--) {
            res += b[i];
            res *= (i > 0 ? CHARS_LENGTH : 1);
        }
        return (res - SLAT) / PRIME1;
    }

    /**
     * 查找对应字符的index
     *
     * @param c 字符
     * @return index
     */
    private static int findIndex(char c) {
        for (int i = 0; i < CHARS_LENGTH; i++) {
            if (CHARS[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static String genCode(long id, CodeType type) {
        switch (type) {
            case USER:
                return gen(id + USER_CODE_OFFSET);
            case ASSIST:
                return gen(id + ASSIST_CODE_OFFSET);
            case ADMIN:
                return gen(id + ADMIN_CODE_OFFSET);
        }
       return null;
    }

    public static ParseCodeResult parseCode(String code) {
        String c = code;
        String extra = StringUtils.EMPTY;
        if (c.length() > CODE_LENGTH) {
            extra = code.substring(CODE_LENGTH);
            c = code.substring(0, CODE_LENGTH);
        }

        Long id = decode(c);
        if (id == null) {
            return null;
        }
        if (id >= ADMIN_CODE_OFFSET) {
            if(StringUtils.isNotEmpty(extra)){
                return new ParseCodeResult(id - ADMIN_CODE_OFFSET, code, CodeType.CHIEF_ASSIST);
            }
            return new ParseCodeResult(id - ADMIN_CODE_OFFSET, code, CodeType.ADMIN);
        }
        if (id >= ASSIST_CODE_OFFSET) {
            return new ParseCodeResult(id - ASSIST_CODE_OFFSET, code, CodeType.ASSIST);
        }
        return new ParseCodeResult(id - USER_CODE_OFFSET, code, CodeType.USER);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParseCodeResult {
        private Long id;
        private String code;
        private CodeType codeType;
    }

    public static enum CodeType {
        ADMIN,
        ASSIST,
        USER,
        CHIEF_ASSIST,
        ;

        public UserRole toUserRole() {
            switch (this) {
                case ADMIN:
                    return UserRole.ADMIN;
                case ASSIST:
                    return UserRole.ASSIST;
                case USER:
                    return UserRole.USER;
                case CHIEF_ASSIST:
                    return UserRole.CHIEF_ASSIST;
            }
            return null;
        }
    }

    public static void main(String... args) {
//        for(int i=0; i< 100; i++){
//            String code = gen(100000L + i);
//            System.out.println(code);
//            System.out.println(decode(code));
//        }

        System.out.println(parseCode("W4S4YBCT"));
        System.out.println(genCode(100392, CodeType.ASSIST));
    }


}
