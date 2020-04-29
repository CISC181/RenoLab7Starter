package pkgEnum;
/*
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
*/
public enum eBetAction {
	
	//@XmlEnumValue("CHECK")
	CHECK("CHECK"), 
	//@XmlEnumValue("BET")
	BET("BET"), 
	//@XmlEnumValue("RAISE")
	RAISE("RAISE"), 
	//@XmlEnumValue("CALL")
	CALL("CALL"), 
	//@XmlEnumValue("FOLD")
	FOLD("FOLD");
	
    private final String value;
    
    eBetAction(String v) {
        value = v;
    }
 
    public String value() {
        return value;
    }
 
    public static eBetAction fromValue(String v) {
        for (eBetAction c: eBetAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
