package site.metacoding.serverproject.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Hospital {

    @Id
    private Integer id;

    private String addr;

    private String mgtStaDd;

    private String pcrPsblYn;

    private String ratPsblYn;

    private Integer recuClCd;

    private String sgguCdNm;

    private String sidoCdNm;

    private String yadmNm;

    private String ykihoEnc;

    private String xposWgs84;

    private String yposWgs84;

    private String xpos;

    private String ypos;
}