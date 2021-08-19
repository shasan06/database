package com.example.archelocapp_1.Models

class Nest {
    var general_comments: String? = null
    var excavation_comments: String? = null
    var live_hatchlings_in_nest = 0
    var dead_hatchlings_in_nest = 0
    var live_embryos_in_unhatched_eggs = 0
    var dead_embryos_in_unhatched_eggs_late = 0
    var dead_embryos_in_unhatched_eggs_middle = 0
    var dead_embryos_in_unhatched_eggs_early = 0
    var dead_embryos_in_unhatched_eggs_eye_spot = 0
    var no_embryos_in_unhatched_eggs = 0
    var pipped_live_hatchlings = 0
    var pipped_dead_hatchlings = 0
    var hatched_eggs = 0
    var excavation_bottom_of_nest_depth = 0
    var excavation_date: String? = null
    var affected_by_light_pollution = false
    var predated_during_hatching = false
    var inundated_during_hatching = false
    var date_of_last_hatching: String? = null
    var date_of_first_hatching: String? = null
    var predated_during_incubation = false
    var inundated_during_incubation = false
    var protection_measures: String? = null
    var subsector: String? = null
    var sector: String? = null
    var beach: String? = null
    var nest_code: String? = null
    var id = 0
}