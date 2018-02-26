package cn.rfatx.common.constant;

public enum ProductCode{
    register("IFTS101"),preAuth("IFTS201"),fastPreAuth("IFTS202"),consume("IFTS203"),preAuthFinish("IBTS201"),preAuthRevoke("IBTS202"),batchPreAuthRevoke("IFTS201"),batchPreAuthFinish("IBTB202"),Refund("IBTS203"),readRegister("IBQS101"),readTransaction("IBQS201"),tjzxsxf("PBTS201"),krzxhysq("IBTS204"),readBoundCard("PBQS101");
    private String code;
    ProductCode(String code){
        this.code=code;
    }

    public String getCode() {
        return this.code;
    }
}
