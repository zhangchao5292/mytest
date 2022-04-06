package com.example.mytest.common.utils;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author zhangchao
 * @Date 2021/8/3
 */
public class ValidationUtil {
    private static Validator validator = ((HibernateValidatorConfiguration)((HibernateValidatorConfiguration) Validation.byProvider(HibernateValidator.class).configure()).failFast(false)).buildValidatorFactory().getValidator();

    public ValidationUtil() {
    }

    public static <T> ValidationUtil.ValidResult validateBean(T t, Class<?>... groups) {
        ValidationUtil.ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t, groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            Iterator var5 = violationSet.iterator();

            while(var5.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var5.next();
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }

        return result;
    }

    public static <T> ValidationUtil.ValidResult validateProperty(T obj, String propertyName) {
        ValidationUtil.ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName, new Class[0]);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            Iterator var5 = violationSet.iterator();

            while(var5.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var5.next();
                result.addError(propertyName, violation.getMessage());
            }
        }

        return result;
    }

    public class ErrorMessage {
        private String propertyPath;
        private String message;

        public ErrorMessage() {
        }

        public ErrorMessage(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        public String getPropertyPath() {
            return this.propertyPath;
        }

        public String getMessage() {
            return this.message;
        }

        public void setPropertyPath(String propertyPath) {
            this.propertyPath = propertyPath;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ValidationUtil.ErrorMessage)) {
                return false;
            } else {
                ValidationUtil.ErrorMessage other = (ValidationUtil.ErrorMessage)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$propertyPath = this.getPropertyPath();
                    Object other$propertyPath = other.getPropertyPath();
                    if (this$propertyPath == null) {
                        if (other$propertyPath != null) {
                            return false;
                        }
                    } else if (!this$propertyPath.equals(other$propertyPath)) {
                        return false;
                    }

                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message != null) {
                            return false;
                        }
                    } else if (!this$message.equals(other$message)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof ValidationUtil.ErrorMessage;
        }

        public int hashCode() {
            int result = 1;
            Object $propertyPath = this.getPropertyPath();
            int resultx = result * 59 + ($propertyPath == null ? 43 : $propertyPath.hashCode());
            Object $message = this.getMessage();
            resultx = resultx * 59 + ($message == null ? 43 : $message.hashCode());
            return resultx;
        }

        public String toString() {
            return "ValidationUtil.ErrorMessage(propertyPath=" + this.getPropertyPath() + ", message=" + this.getMessage() + ")";
        }
    }

    public class ValidResult {
        private boolean hasErrors;
        private List<ErrorMessage> errors = new ArrayList();

        public ValidResult() {
        }

        public boolean hasErrors() {
            return this.hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        public List<ValidationUtil.ErrorMessage> getAllErrors() {
            return this.errors;
        }

        public String getErrors() {
            StringBuilder sb = new StringBuilder();
            Iterator var2 = this.errors.iterator();

            while(var2.hasNext()) {
                ValidationUtil.ErrorMessage error = (ValidationUtil.ErrorMessage)var2.next();
                sb.append(error.getPropertyPath()).append(":").append(error.getMessage()).append(" ");
            }

            return sb.toString();
        }

        public void addError(String propertyName, String message) {
            this.errors.add(ValidationUtil.this.new ErrorMessage(propertyName, message));
        }

        public boolean isHasErrors() {
            return this.hasErrors;
        }

        public void setErrors(List<ValidationUtil.ErrorMessage> errors) {
            this.errors = errors;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ValidationUtil.ValidResult)) {
                return false;
            } else {
                ValidationUtil.ValidResult other = (ValidationUtil.ValidResult)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.isHasErrors() != other.isHasErrors()) {
                    return false;
                } else {
                    Object this$errors = this.getErrors();
                    Object other$errors = other.getErrors();
                    if (this$errors == null) {
                        if (other$errors != null) {
                            return false;
                        }
                    } else if (!this$errors.equals(other$errors)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof ValidationUtil.ValidResult;
        }

        public int hashCode() {
            int resultx = 1;
            int result = resultx * 59 + (this.isHasErrors() ? 79 : 97);
            Object $errors = this.getErrors();
            result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
            return result;
        }

        public String toString() {
            return "ValidationUtil.ValidResult(hasErrors=" + this.isHasErrors() + ", errors=" + this.getErrors() + ")";
        }
    }
}
