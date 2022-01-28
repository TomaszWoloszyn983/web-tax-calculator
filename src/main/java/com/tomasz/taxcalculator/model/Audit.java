package com.tomasz.taxcalculator.model;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

/**
 * This class contains data extracted from Person class.
 * These data are used for auditing when all element were added
 * or updated in our database.
 *
 * Thanks to annotation Embedded the class can be implemented to the Person class.
 */
@Embeddable
class Audit {
    private LocalDate createdOn;
    private LocalDate updatedOn;

    /**
     * prePersist adds the date when our object was created and
     * written in our database.
     */
    @PrePersist
    void prePersist(){
        createdOn = LocalDate.now();
    }

    /**
     * preMerge initailizes the date of when our object was updated.
     */
    @PreUpdate
    void preMerge(){
        updatedOn = LocalDate.now();
    }
}
