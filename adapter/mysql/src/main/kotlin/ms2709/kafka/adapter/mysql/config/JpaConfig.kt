package ms2709.kafka.adapter.mysql.config

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

//@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = ["ms2709.kafka"]
)
@Configuration
open class JpaConfig(
    private val jpaProperties: JpaProperties,
    private val hibernateProperties: HibernateProperties
) {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    open fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }


    @Bean
    @Primary
    open fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val properties = hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings())
        return builder.dataSource(dataSource())
            .packages("ms2709.kafka")
            .properties(properties)
            .build()
    }

}