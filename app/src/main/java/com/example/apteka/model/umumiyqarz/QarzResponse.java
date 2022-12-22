package com.example.apteka.model.umumiyqarz;

import com.google.gson.annotations.SerializedName;

public class QarzResponse {
    @SerializedName("umumiy_sotilgan_summa")
    int umumiy_sotilgan_summa;
    @SerializedName("nechi_marta_sotgan")
    int nechi_marta_sotgan;

    public QarzResponse(int umumiy_sotilgan_summa, int nechi_marta_sotgan) {
        this.umumiy_sotilgan_summa = umumiy_sotilgan_summa;
        this.nechi_marta_sotgan = nechi_marta_sotgan;
    }

    public int getUmumiy_sotilgan_summa() {
        return umumiy_sotilgan_summa;
    }

    public void setUmumiy_sotilgan_summa(int umumiy_sotilgan_summa) {
        this.umumiy_sotilgan_summa = umumiy_sotilgan_summa;
    }

    public int getNechi_marta_sotgan() {
        return nechi_marta_sotgan;
    }

    public void setNechi_marta_sotgan(int nechi_marta_sotgan) {
        this.nechi_marta_sotgan = nechi_marta_sotgan;
    }
}
