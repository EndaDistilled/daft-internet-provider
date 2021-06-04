package ie.dsch.daft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "broadband_packages")
public class BroadbandPackage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "generator")
  @SequenceGenerator(name = "generator", sequenceName = "broadband_packages_seq", allocationSize = 1)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column()
  private String ispName;

  @Column()
  private String packageName;

  @Column
  private String packageSpeed;

  @Column()
  private String region;

  @Column()
  private String place;

}
