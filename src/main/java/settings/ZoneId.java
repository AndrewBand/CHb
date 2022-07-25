package settings;

public enum ZoneId {
    UTCZERO(0,"UTC 0", true),
    UTCONE (1,"UTC +1", false),
    UTCTWO (2,"UTC +2", false),
    UTCTHREE (3, "UTC +3", false),
    UTCFOUR (4,"UTC +4", false),
    UTCFIVE (5,"UTC +5", false),
    UTCSIX (6,"UTC +6", false),
    UTCSEVEN (7,"UTC +7", false),
    UTCEIGHT (8,"UTC +8", false),
    UTCNINE (9,"UTC +9", false),
    UTCTEN (10,"UTC +10", false),
    UTCELEVEN (11,"UTC +11", false),
    UTCTWELVE (12,"UTC +12", false),
    UTCMINUSONE (-1,"UTC -1", false),
    UTCMINUSTWO (-2,"UTC -2", false),
    UTCMINUSTHREE (-3,"UTC -3", false),
    UTCMINUSFOUR (-4,"UTC -4", false),
    UTCMINUSFIVE (-5,"UTC -5", false),
    UTCMINUSSIX (-6,"UTC -6", false),
    UTCMINUSSEVEN (-7,"UTC -7", false),
    UTCMINUSEIGHT (-8,"UTC -8", false),
    UTCMINUSNINE (-9,"UTC -9", false),
    UTCMINUSTEN (-10,"UTC -10", false),
    UTCMINUSELEVEN (-11,"UTC -11", false),
    UTCMINUSTWELVE (-12,"UTC -12", false);
    private int zone;

    private String nameZone;
    private boolean select;


    ZoneId(int zone,String nameZone, boolean select) {
        this.zone = zone;
        this.nameZone = nameZone;
        this.select = select;
    }
    public int getZone() {
        return zone;
    }
    public String getNameZone(){
        return nameZone;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public static String getButtonStatus (ZoneId button){
        if(button.isSelect()){
            return "✅";
        }
        return "";
    }
}