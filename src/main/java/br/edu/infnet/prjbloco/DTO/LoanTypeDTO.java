package br.edu.infnet.prjbloco.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanTypeDTO {


    private Integer id;

    private String name;

    private Integer gracePeriod;

    private BigDecimal interestRate;

    private Integer loanTerm;

}
