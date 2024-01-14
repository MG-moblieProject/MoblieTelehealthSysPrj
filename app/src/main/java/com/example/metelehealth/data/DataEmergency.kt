package com.example.metelehealth.data

import com.example.metelehealth.model.EmergencyNumbers

class DataEmergency {

    fun loadCalls(): List<EmergencyNumbers> {
        return listOf(
            EmergencyNumbers("Quận 1", "Bệnh viện Nhi Đồng 2", "1900 1215"),
            EmergencyNumbers("Quận 10", "Bệnh viện Nhi Đồng 1", "028 3927 1119"),
            EmergencyNumbers("Quận 3", "Bệnh Viện Y Học Cổ Truyền", "096 7991 010"),
            EmergencyNumbers("Bình Thạnh", "Bệnh Viện Ung Bướu", "096 7981 010"),
            EmergencyNumbers("Thủ Đức", "Bệnh Viện Quận Thủ Đức", "096 6321 010"),
            EmergencyNumbers("Quận 3", "Bệnh Viện Bình Dân", "096 7871 010"),
            EmergencyNumbers("Quận 5", "Bệnh Viện Hùng Vương", "096 7741 010"),
            EmergencyNumbers("Quận 5", "Bệnh Viện Hùng Vương", "096 7741 010"),
            EmergencyNumbers("Quận 10", "Bệnh Viện Nhân Dân 115", "096 7701 010"),
            EmergencyNumbers("Thủ Đức", "Bệnh Viện Đa Khoa Khu Vực Thủ Đức", "096 6281 010"),
            EmergencyNumbers("Quận 5", "Bệnh Viện Bệnh Nhiệt Đới", "096 7741 010"),

            )
    }

}