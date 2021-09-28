package senior.juc.lock.countdownlatch;

/**
 * 定义枚举类：可以通过 CountryEnum.ONE 获得齐国对应的 CountryEnum 对象
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-28-22:10
 */
public enum  CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    private Integer retCode;
    private String retMsg;

    CountryEnum(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public static CountryEnum list(int idx) {
        // 获取枚举类中的所有值
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (idx == countryEnum.getRetCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
