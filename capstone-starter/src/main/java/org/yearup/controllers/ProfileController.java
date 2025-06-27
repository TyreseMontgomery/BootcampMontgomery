package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {
    private final ProfileDao profileDao;
    private final UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userDao.getByUserName(username);

            if (user == null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            Profile profile = profileDao.getByUserId(user.getId());

            if (profile == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateProfile(@RequestBody Profile profile) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userDao.getByUserName(username);

            if (user == null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            profile.setUserId(user.getId());
            profileDao.update(profile);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
