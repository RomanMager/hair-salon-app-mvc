package by.bsuir.hairdressingsalon.hairsalonapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "procedure_appointment")
public class ProcedureAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "appointment_time")
    private LocalTime startTime;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "procedure_id")
    private SalonProcedure salonProcedure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer signedUpCustomer;

    @OneToOne
    @LazyToOne(LazyToOneOption.PROXY)
    @JoinColumn(name = "performing_employee_id", referencedColumnName = "id")
    private Employee performingEmployee;

    @Override
    public String toString() {
        return "ProcedureAppointment{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", signedUpCustomer=" + signedUpCustomer +
                '}';
    }
}
