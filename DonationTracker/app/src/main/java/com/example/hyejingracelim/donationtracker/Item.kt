package com.example.hyejingracelim.donationtracker

import java.io.Serializable

/**
 * Stores item information
 */
class Item : Serializable {

    var donationTime: String? = null
    var location: String? = null
    var value: String? = null
    var shortDescription: String? = null
    var longDescription: String? = null
    var category: String? = null

    /**
     * Converts item to string
     * @return item as string
     */
    override fun toString(): String {
        if (donationTime != null) {
            var ans = ""
            ans = ans + "Time : "
            ans = ans + donationTime!!
            if (location != null) {
                ans = "$ans Location : "
                ans = ans + location!!
            }
            if (category != null) {
                ans = "$ans Category : "
                ans = ans + category!!
            }
            if (longDescription != null) {
                ans = "$ans Description : "
                ans = ans + longDescription!!
            }
            if (value != null) {
                ans = "$ans Value : "
                ans = ans + value!!
            }
            return ans
        } else {
            return "Time not Recorded"
        }
    }
}
