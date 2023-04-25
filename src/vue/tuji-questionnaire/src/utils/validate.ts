// const SENSITIVE_REGEX = /[\u4e00-\u9fa5]|[^\w\s]/g;
 export const SENSITIVE_REGEX = /[\^\$\.\*\+\?\|\[\]\(\)\{\}\\\/]/g;



export const validateSensitive = (rule :any, value:any, callback:any) => {
    if (value === '') {
        callback(new Error('请输入内容'))
    }else {
        if (SENSITIVE_REGEX.test(value)) {
            callback(new Error('输入内容包含敏感字符'));
        } else {
            callback();
        }
    }
};