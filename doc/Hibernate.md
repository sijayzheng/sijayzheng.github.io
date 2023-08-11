## 异常

### org.hibernate.MappingException

hibernate 映射文件错误

## Hibernate-Validator 主要的校验注解：

| 注解                      | 功能                                                                          |
| ------------------------- | ----------------------------------------------------------------------------- |
| @Null                     | 验证对象是否为 null                                                           |
| @NotNull                  | 验证对象是否不为 null                                                         |
| @AssertTrue               | 验证 Boolean 对象是否为 true                                                  |
| @AssertFalse              | 验证 Boolean 对象是否为 false                                                 |
| @Max(value)               | 验证 Number 和 String 对象是否小于等于指定值                                  |
| @Min(value)               | 验证 Number 和 String 对象是否大于等于指定值                                  |
| @DecimalMax(value)        | 验证注解的元素值小于等于 @DecimalMax 指定的 value 值                          |
| @DecimalMin(value)        | 验证注解的元素值大于等于 @DecimalMin 指定的 value 值                          |
| @Digits(integer,fraction) | 验证字符串是否符合指定格式的数字，integer 指定整数精度，fraction 指定小数精度 |
| @Size(min,max)            | 验证对象长度是否在给定的范围内                                                |
| @Past                     | 验证 Date 和 Calendar 对象是否在当前时间之前                                  |
| @Future                   | 验证 Date 和 Calendar 对象是否在当前时间之后                                  |
| @Pattern                  | 验证 String 对象是否符合正则表达式的规则                                      |
| @NotBlank                 | 检查字符串是不是 Null，被 Trim 的长度是否大于 0，只对字符串，且会去掉前后空格 |
| @URL                      | 验证是否是合法的 url                                                          |
| @Email                    | 验证是否是合法的邮箱                                                          |
| @CreditCardNumber         | 验证是否是合法的信用卡号                                                      |
| @Length(min,max)          | 验证字符串的长度必须在指定范围内                                              |
| @NotEmpty                 | 检查元素是否为 Null 或 Empty                                                  |
| @Range(min,max,message)   | 验证属性值必须在合适的范围内                                                  |
