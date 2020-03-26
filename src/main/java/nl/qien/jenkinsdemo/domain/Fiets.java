package nl.qien.jenkinsdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fiets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fietsId;

    private String manufacturer;
    private String model;
    private int serialNumber;

    public Long getFietsId() {
        return fietsId;
    }

    public void setFietsId(Long fietsId) {
        this.fietsId = fietsId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
