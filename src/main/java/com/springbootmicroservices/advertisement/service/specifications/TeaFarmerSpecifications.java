package com.springbootmicroservices.advertisement.service.specifications;

import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TeaFarmerSpecifications {

    public static Specification<TeaFarmer> hasGrowerNumber(String growerNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("growerNumber"), growerNumber);
    }

    public static Specification<TeaFarmer> hasGrowerName(String growerName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("growerName"), growerName);
    }

    public static Specification<TeaFarmer> hasBuyingCentre(String buyingCentre) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("buyingCentre"), buyingCentre);
    }

    public static Specification<TeaFarmer> hasNationalId(String nationalId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nationalId"), nationalId);
    }

    public static Specification<TeaFarmer> hasGrowerGroup(String growerGroup) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("growerGroup"), growerGroup);
    }

    // Add specifications for other String fields similarly

    public static Specification<TeaFarmer> hasTotalLandAreaAcres(double totalLandAreaAcres) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("totalLandAreaAcres"), totalLandAreaAcres);
    }

    // Add specifications for other double fields similarly

    public static Specification<TeaFarmer> hasTotalTeaBushes(Integer totalTeaBushes) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("totalTeaBushes"), totalTeaBushes);
    }

    public static Specification<TeaFarmer> hasAgeOfTeaBushYears(Integer ageOfTeaBushYears) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("ageOfTeaBushYears"), ageOfTeaBushYears);
    }

    // Add specifications for other Integer fields similarly

    public static Specification<TeaFarmer> hasProductivityPerBushYear(BigDecimal productivityPerBushYear) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("productivityPerBushYear"), productivityPerBushYear);
    }

    // Add specifications for other BigDecimal fields similarly

    // Add specifications for relationships (e.g., Factory, Ward, SubCounty, etc.)

    public static Specification<TeaFarmer> buildSpec(String growerNumber, String growerName, String buyingCentre, String nationalId,
                                                     String growerGroup, double totalLandAreaAcres) {
        Specification<TeaFarmer> spec = Specification.where(null);

        if (growerNumber != null) {
            spec = spec.and(hasGrowerNumber(growerNumber));
        }

        if (growerName != null) {
            spec = spec.and(hasGrowerName(growerName));
        }

        if (buyingCentre != null) {
            spec = spec.and(hasBuyingCentre(buyingCentre));
        }

        if (nationalId != null) {
            spec = spec.and(hasNationalId(nationalId));
        }

        if (growerGroup != null) {
            spec = spec.and(hasGrowerGroup(growerGroup));
        }

        if (totalLandAreaAcres > 0) {
            spec = spec.and(hasTotalLandAreaAcres(totalLandAreaAcres));
        }

        // Add more conditions for other fields

        return spec;
    }
}
