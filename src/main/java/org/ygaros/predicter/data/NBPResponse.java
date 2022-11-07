package org.ygaros.predicter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NBPResponse {

    private String table;

    private String currency;

    private String code;

    private Rate[] rates;
}
