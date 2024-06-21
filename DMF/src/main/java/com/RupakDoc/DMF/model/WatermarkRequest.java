package com.RupakDoc.DMF.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatermarkRequest {

    private long application_transaction_id;
    private String watermark;
}

