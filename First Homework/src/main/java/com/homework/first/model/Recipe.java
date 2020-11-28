package com.homework.first.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "recipes")
public class Recipe {
    @Id
    @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
    private String id;
    @Pattern(regexp = "^[A-Fa-f0-9]{24}$")
    private String userId;
    @NonNull
    @NotNull
    @Size(max=80)
    private String name;
    @NonNull
    @NotNull
    @Size(max=256)
    private String shortDescription;
    @NonNull
    @NotNull
    private int preparationTime;

    private List<String> requiredProducts = new ArrayList<>();
    @URL
    private String photoUrl;
    @NonNull
    @NotNull
    @Size(max=2048)
    private String detailedDescription;

    private List<String> tags = new ArrayList<>();

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

    public Recipe(@NonNull @NotNull @Size(max = 80) String name,
                  @NonNull @NotNull @Size(max = 256) String shortDescription,
                  @NonNull @NotNull int preparationTime,
                  List<String> requiredProducts,
                  @URL String photoUrl,
                  @NonNull @NotNull @Size(max = 2048) String detailedDescription,
                  List<String> tags) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.preparationTime = preparationTime;
        this.requiredProducts = requiredProducts;
        this.photoUrl = photoUrl;
        this.detailedDescription = detailedDescription;
        this.tags = tags;
    }
}
