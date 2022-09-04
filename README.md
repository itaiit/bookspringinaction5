# bookspringinaction5

这是学习《Spring实战》（第五版）的学习记录仓库

# 前七章整理

## 1. 前端界面使用angular；

导航组件为：header.component.html；

查看每个导航链接的路由：app.routes.ts；

## 2. 数据库表结构：

1. Ingredient：

```sql
CREATE TABLE `Ingredient` (
   `id` varchar(4) NOT NULL,
   `name` varchar(25) NOT NULL,
   `type` varchar(10) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
```

2. Taco：

```sql
CREATE TABLE `Taco` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4
```

3. Taco_Ingredient：

```sql
CREATE TABLE `Taco_Ingredient` (
   `taco` bigint(20) NOT NULL,
   `ingredient` varchar(4) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
```

## 3. Taco，Ingredient实体类使用Spring JPA持久化机制：

需要设置JPA的逻辑实体名称与物理表的映射关系：（配置数据库表和字段是否需要做转换，比如驼峰命名的字段，使用下划线：createdAt->
created_at）

```yaml
spring:
	jpa:
    hibernate:
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

- Taco实体类的表名为：Taco；
- Ingredient实体类的表名为：Ingredient；
- 在使用JPA的情况下，如果需要关联查询，使用@ManyToMany注解的时候，可以指定关联关系所在的数据库表；然后使用@JoinTable进行指定，并指定关联实体之间的join方式：

```java
@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;
    private String name;
    @ManyToMany // 默认的连接表为taco_ingredient
    @JoinTable(
            joinColumns = @JoinColumn(name = "taco", referencedColumnName = "id"), // owning side
            inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id")
    )
    private List<Ingredient> ingredients;
}
```

## 4. REST API的暴露：

- 使用JPA，并且添加了spring-boot-starter-data-rest依赖，则会自动将JPA Repository暴露为Rest API端点；

查看暴露的所有端点，访问路径为：localhost:8080/api；

工程：tacocloud-data；

- 使用Hateoas暴露REST API，将taco和Ingredient封装成资源RepresentationModel，然后通过RepresentationModelAssemblerSupport，为资源添加link链接；
  - ~~将design暴露为REST；~~
  - 将Ingredient暴露为REST；

访问路径为：localhost:8080/hateoas/design/recents；

工程：tacocloud-api；

# 第八章

添加工程：

- tacocloud-messaging-rabbit：使用rabbitmq；向mq发送order；
- tacocloud-kitchen：接收rabbitmq队列的消息；手动的接收/监听接收；

发送到rabbitmq的消息使用Jackson2JsonMessageConverter的messageconverter实例；

> 需要注意，order中有user对象的数据，而user是一个userservice；默认json转化的时候，user对象，会生成一个：
>
> ```json
>         "authorities": [
>             {
>                 "authority": "USER"
>             }
>         ]
> ```
>
>
的json数据，在接收当队列数据，转换为order的时候，这个json结构会转换失败；可以选择在将order转换为json的时候，不json化authorities字段：添加` @JsonIgnore`
注解：
>
> ```java
>     @JsonIgnore
>     @Override
>     public Collection<? extends GrantedAuthority> getAuthorities() {
>         return Arrays.asList(new SimpleGrantedAuthority("USER"));
>     }
> ```
>
>

当创建完订单之后，访问：`http://localhost:8080/orders/receive`，可以获取队列中的order数据；

