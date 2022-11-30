package org.exam.domain.type;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PointType {

    EVERY_DAY(100L, "P1"),
    THREE_DAYS(300L, "P2"),
    FIVE_DAYS(500L, "P3"),
    TEN_DAYS(1000L, "P4")
    ;

    private Long point;
    private String code;


    public static PointType codeToType(String code){

        for (PointType type : PointType.values()){
            if(Objects.equals(type.getCode(), code))
                return type;
        }

        return null;
    }


    @Converter
    public static class TypeConvert implements AttributeConverter<PointType, String> {

        @Override
        public String convertToDatabaseColumn(PointType input) {
            return (input != null) ? input.getCode() : null;
        }

        @Override
        public PointType convertToEntityAttribute(String db) {
            return PointType.codeToType(db);
        }

    }


}
