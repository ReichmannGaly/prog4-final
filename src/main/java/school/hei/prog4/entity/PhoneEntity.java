package school.hei.prog4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "phone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String phoneNumber;

    @Column(name = "code_pays")
    @Enumerated(EnumType.STRING)
    private Code codePays;

    public enum Code{
        FR, MDG
    }

    @PrePersist
    public void reformatPhoneNumber(){
        if (codePays == Code.FR){
            phoneNumber = "+33" + phoneNumber.substring(1);
        }
        else if (codePays == Code.MDG){
            phoneNumber = "+261" + phoneNumber.substring(1);
        }
    }
}
