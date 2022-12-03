package org.exam.domain.type;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum PointType {

    EVERY_DAY(100L,1L, "P1"),
    THREE_DAYS(300L, 3L, "P2"),
    FIVE_DAYS(500L,5L, "P3"),
    TEN_DAYS(1000L, 10L, "P4")
    ;

    private Long point;
    private Long value;
    private String code;

    public static PointType codeToType(String code){
        for (PointType type : PointType.values()){
            if(ObjectUtils.nullSafeEquals(type.getCode(), code))
                return type;
        }
        return null;
    }

    public static Optional<PointType> findAdditionalPoint(Long continuousAttendanceCnt) {
        for (PointType type : PointType.values()){
            if(type.equals(PointType.EVERY_DAY)) continue;
            if(type.getValue().equals(continuousAttendanceCnt)) return Optional.of(type);
        }
        return Optional.empty();
    }

    @Converter
    public static class TypeConvert implements AttributeConverter<PointType, String> {

        @Override
        public String convertToDatabaseColumn(PointType input) {
            return (Objects.nonNull(input)) ? input.getCode() : null;
        }

        @Override
        public PointType convertToEntityAttribute(String db) {
            return PointType.codeToType(db);
        }
    }


}
