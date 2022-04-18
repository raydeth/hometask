package domain;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private Map<Currency, BigDecimal> wallets;

}
