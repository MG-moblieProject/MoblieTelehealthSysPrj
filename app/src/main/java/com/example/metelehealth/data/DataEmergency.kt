package com.example.metelehealth.data

import com.example.metelehealth.model.EmergencyNumbers

class DataEmergency {

    fun loadCalls(): List<EmergencyNumbers> {
        return listOf(
            EmergencyNumbers("Nairobi County", "Kijabe Hospital", "0702020202"),
            EmergencyNumbers("Nakuru County", "Kabarak Hospital", "0703030303"),
            EmergencyNumbers("kisumu County", "Mercy Hospital", "0704040404"),
            EmergencyNumbers("Mombasa County", "Mediheal Hospital", "0705050505"),
            EmergencyNumbers("Nakuru County", "PGH Nakuru", "0706060606"),
            EmergencyNumbers("Nakuru County", "Mogotio sub-county", "0707070707"),
            EmergencyNumbers("Baringo County", "Kabarnet Refferal ", "0708080808"),
            EmergencyNumbers("Nairobi County", "KU Hospital ", "0709090909"),
            EmergencyNumbers("Nairobi County", "Keringet sub-county", "0710101010"),
            EmergencyNumbers("Nakuru County", "Molo Hospital", "0711111111"),
            EmergencyNumbers("Eldoret County", "Moi Refferal", "0712121212"),
            EmergencyNumbers("UasinG County", "Eldoret District", "0713131313"),
            EmergencyNumbers("Nakuru County", "Mirugi sub-county", "0714141414"),
            EmergencyNumbers("Kericho County", "Bliss Hospital", "0715151515"),
            EmergencyNumbers("Nairobi County", "Nairobi Hospital", "071616161"),
            EmergencyNumbers("Nairobi County", "Nairobi Womens", "0717171717")
        )
    }

}