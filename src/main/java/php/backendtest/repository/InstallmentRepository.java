package php.backendtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import php.backendtest.entity.Installment;

import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    @Query(value = "SELECT * FROM INSTALLMENT WHERE PAYMENT_INFO_ID = ?1", nativeQuery = true)
    List<Installment> findAllByPaymentId(Long paymentId);

}
