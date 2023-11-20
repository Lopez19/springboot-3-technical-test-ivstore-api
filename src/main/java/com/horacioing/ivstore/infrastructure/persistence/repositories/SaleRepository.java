package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    @Query(nativeQuery = true,
            value =
                    """
                            select s.name, count(*) as total_sales
                            from sales
                                     inner join stores s on sales.store_id = s.id
                            group by s.name
                            order by total_sales desc;
                            """
    )
    List<?> getSalesGroupByStore();

}