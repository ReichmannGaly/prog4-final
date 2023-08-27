package school.hei.prog4.utils;

import school.hei.prog4.entity.PhoneEntity;

public class PhoneUtils {
    public static PhoneEntity addStateCode(PhoneEntity phone) {
        if (phone.getCodePays() == PhoneEntity.Code.FR) {
            phone.setPhoneNumber("+33" + phone.getPhoneNumber().substring(1));
        } else if (phone.getCodePays() == PhoneEntity.Code.MDG) {
            phone.setPhoneNumber("+261" + phone.getPhoneNumber().substring(1));
        }

        return phone;
    }
}
