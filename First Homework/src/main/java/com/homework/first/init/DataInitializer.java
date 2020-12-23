package com.homework.first.init;

import com.homework.first.model.Recipe;
import com.homework.first.model.User;
import com.homework.first.service.RecipeService;
import com.homework.first.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.homework.first.model.Role.ADMIN;
import static com.homework.first.model.Role.USER;


@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    private static final List<User> SAMPLE_USERS = Arrays.asList(
            new User("Admin", "Admin", "admin123", "cannot say",
                    "https://lh3.googleusercontent.com/proxy/cGNVbclL0E2LBuUnIxUaC7d-wP_K18xwNUMVzCHmtxgdEtaknGpKCZz-rBVUWP46jCXJSPq6Va7hZ__JZVjG4EKwLx-Kezlk9Qtb5NDc9Gb-E1oq85KV",
                    "Admin of the API",
                    new HashSet<>(Arrays.asList(USER, ADMIN)))
            ,
            new User("Matt", "Cooker", "cooker123", "male",
                    "https://cdn.iconscout.com/icon/premium/png-512-thumb/public-domain-user-618551.png",
                    "The best meat cooker in the Balkans",
                    new HashSet<>(Collections.singletonList(USER))
            ));
    private static final List<Recipe> SAMPLE_RECIPES = Collections.singletonList(
            new Recipe("Musaka",
                    "Traditional bulgarian dish",
                    120,
                    new ArrayList<>(Arrays.asList("Kaima", "Jogurt", "Eggs")),
                    "Musaka is very good bulgarian dish, every woman should know how to make one",
                    "https://musaka.com/bulgarian-dishes/muska.png",
                    new ArrayList<>(Arrays.asList("Bulgarian", "Meat", "Taste")))

    );


    @Override
    public void run(String... args) {
        if (userService.getCount() == 0) {
            SAMPLE_USERS.forEach(userService::addUser);
        }
//        if (recipeService.getCount() == 0) {
//            SAMPLE_RECIPES.forEach(recipe -> {
//                recipe.setUserId(userService.getUserByUsername("Cooker").getId());
//                recipeService.createRecipe(recipe);
//            });
//        }
    }
}
