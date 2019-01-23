package logan.common.base.test.vo;

import java.util.List;

/**
 * @author zhoupan
 */
public class FeeInfo {

    /**
     * 机构
     * this is orgCode
     * Y
     */
    private String orgCode;

    /**
     * 报案号
     * this is caseNo
     * Y
     */
    private String caseNo;

    /**
     * 损失项id
     * this is lossId
     * Y
     */
    private String lossId;

    /**
     * 定损员编号
     * this is partFeeCode
     * Y
     */
    private String partFeeCode;

    /**
     * 定损员姓名
     * this is partFeeName
     * Y
     */
    private String partFeeName;

    /**
     * 定损员电话
     * this is partFeePh
     * Y
     */
    private String partFeePh;

    /**
     * 车架号
     * this is vin
     * N
     */
    private String vin;

    /**
     * 厂牌型号
     * this is brandType
     * N
     */
    private String brandType;

    /**
     * 发动机号
     * this is engineNo
     * N
     */
    private String engineNo;

    /**
     * 驾驶员名称
     * this is driverName
     * N
     */
    private String driverName;

    /**
     * 配件总金额
     * this is partsTotalMoney
     * Y
     */
    private String partsTotalMoney;

    /**
     * 配件列表
     * this is parts
     * Y
     */
    private List<Part> parts;

    /**
     * 工时总金额
     * this is laborHoursTotalMoney
     * Y
     */
    private String laborHoursTotalMoney;

    /**
     * 工时列表
     * this is laborHours
     * N
     */
    private List<LaborHour> laborHours;

    /**
     * 辅料总金额
     * this is ingredientsTotalMoney
     * Y
     */
    private String ingredientsTotalMoney;

    /**
     * 辅料列表
     * this is ingredients
     * N
     */
    private List<Ingredients> ingredients;

    /**
     * 外修总金额
     * this is benefitPeoplesTotalMoney
     * Y
     */
    private String benefitPeoplesTotalMoney;

    /**
     * 外修列表
     * this is benefitPeoples
     * N
     */
    private List<BenefitPeople> benefitPeoples;

    /**
     * 定损金额
     * this is feeAmount
     * Y
     */
    private String feeAmount;

    /**
     * 施救总金额
     * this is rescuesTotalMoney
     * Y
     */
    private String rescuesTotalMoney;

    /**
     * 施救列表
     * this is rescues
     * N
     */
    private List<Rescue> rescues;

    /**
     * 残值总金额
     * this is salvagesTotalMoney
     * Y
     */
    private String salvagesTotalMoney;

    /**
     * 残值列表
     * this is salvages
     * N
     */
    private List<Salvage> salvages;

    /**
     * 定损总金额
     * this is totalFeeAmount
     * Y
     */
    private String totalFeeAmount;

    /**
     * 推荐修理厂
     * this is repairShopName
     * Y
     */
    private String repairShopName;

    /**
     * 推荐修理厂编码
     * this is repairShopCode
     * N
     */
    private String repairShopCode;

    /**
     * 价格方案
     * this is priceType
     * Y
     */
    private String priceType;

    /**
     * 出险原因
     * this is accidentCause
     * Y
     */
    private String accidentCause;

    /**
     * 是否全损
     * this is totalLoss
     * Y
     */
    private String totalLoss;

    /**
     * 照片视频路径
     * this is ossKeys
     * N
     */
    private List<String> ossKeys;

    /**
     * 备注
     * this is remarks
     * N
     */
    private String remarks;

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public void setLossId(String lossId) {
        this.lossId = lossId;
    }

    public String getLossId() {
        return this.lossId;
    }

    public void setPartFeeCode(String partFeeCode) {
        this.partFeeCode = partFeeCode;
    }

    public String getPartFeeCode() {
        return this.partFeeCode;
    }

    public void setPartFeeName(String partFeeName) {
        this.partFeeName = partFeeName;
    }

    public String getPartFeeName() {
        return this.partFeeName;
    }

    public void setPartFeePh(String partFeePh) {
        this.partFeePh = partFeePh;
    }

    public String getPartFeePh() {
        return this.partFeePh;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVin() {
        return this.vin;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getBrandType() {
        return this.brandType;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setPartsTotalMoney(String partsTotalMoney) {
        this.partsTotalMoney = partsTotalMoney;
    }

    public String getPartsTotalMoney() {
        return this.partsTotalMoney;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public void setLaborHoursTotalMoney(String laborHoursTotalMoney) {
        this.laborHoursTotalMoney = laborHoursTotalMoney;
    }

    public String getLaborHoursTotalMoney() {
        return this.laborHoursTotalMoney;
    }

    public void setLaborHours(List<LaborHour> laborHours) {
        this.laborHours = laborHours;
    }

    public List<LaborHour> getLaborHours() {
        return this.laborHours;
    }

    public void setIngredientsTotalMoney(String ingredientsTotalMoney) {
        this.ingredientsTotalMoney = ingredientsTotalMoney;
    }

    public String getIngredientsTotalMoney() {
        return this.ingredientsTotalMoney;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public void setBenefitPeoplesTotalMoney(String benefitPeoplesTotalMoney) {
        this.benefitPeoplesTotalMoney = benefitPeoplesTotalMoney;
    }

    public String getBenefitPeoplesTotalMoney() {
        return this.benefitPeoplesTotalMoney;
    }

    public void setBenefitPeoples(List<BenefitPeople> benefitPeoples) {
        this.benefitPeoples = benefitPeoples;
    }

    public List<BenefitPeople> getBenefitPeoples() {
        return this.benefitPeoples;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeAmount() {
        return this.feeAmount;
    }

    public void setRescuesTotalMoney(String rescuesTotalMoney) {
        this.rescuesTotalMoney = rescuesTotalMoney;
    }

    public String getRescuesTotalMoney() {
        return this.rescuesTotalMoney;
    }

    public void setRescues(List<Rescue> rescues) {
        this.rescues = rescues;
    }

    public List<Rescue> getRescues() {
        return this.rescues;
    }

    public void setSalvagesTotalMoney(String salvagesTotalMoney) {
        this.salvagesTotalMoney = salvagesTotalMoney;
    }

    public String getSalvagesTotalMoney() {
        return this.salvagesTotalMoney;
    }

    public void setSalvages(List<Salvage> salvages) {
        this.salvages = salvages;
    }

    public List<Salvage> getSalvages() {
        return this.salvages;
    }

    public void setTotalFeeAmount(String totalFeeAmount) {
        this.totalFeeAmount = totalFeeAmount;
    }

    public String getTotalFeeAmount() {
        return this.totalFeeAmount;
    }

    public void setRepairShopName(String repairShopName) {
        this.repairShopName = repairShopName;
    }

    public String getRepairShopName() {
        return this.repairShopName;
    }

    public void setRepairShopCode(String repairShopCode) {
        this.repairShopCode = repairShopCode;
    }

    public String getRepairShopCode() {
        return this.repairShopCode;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceType() {
        return this.priceType;
    }

    public void setAccidentCause(String accidentCause) {
        this.accidentCause = accidentCause;
    }

    public String getAccidentCause() {
        return this.accidentCause;
    }

    public void setTotalLoss(String totalLoss) {
        this.totalLoss = totalLoss;
    }

    public String getTotalLoss() {
        return this.totalLoss;
    }

    public void setOssKeys(List<String> ossKeys) {
        this.ossKeys = ossKeys;
    }

    public List<String> getOssKeys() {
        return this.ossKeys;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return this.remarks;
    }
}