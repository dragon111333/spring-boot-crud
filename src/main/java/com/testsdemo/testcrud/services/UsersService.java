package com.testsdemo.testcrud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.UsersRepo;

@Service
public class UsersService {
	
    @Autowired
    private UsersRepo userRepo;

	public Iterable<User> getAll() {
		try {
			return this.userRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public User getById(int id) {
		try {
			return this.userRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	public User add(User user) {
		
		 User n = new User();
		  
         if (user.getProfile() != null) {
             n.setProfile(user.getProfile());
         }
         if (user.getCover() != null) {
             n.setCover(user.getCover());
         }
         if (user.getUsername() != null) {
             n.setUsername(user.getUsername());
         }
         if (user.getName() != null) {
             n.setName(user.getName());
         }
         if (user.getLastname() != null) {
             n.setLastname(user.getLastname());
         }
         if (user.getNickname() != null) {
             n.setNickname(user.getNickname());
         }
         if (user.getPosition() != null) {
             n.setPosition(user.getPosition());
         }
         if (user.getNationality() != null) {
             n.setNationality(user.getNationality());
         }
         if (user.getTel() != null) {
             n.setTel(user.getTel());
         }
         if (user.getStartingDate() != null) {
             n.setStartingDate(user.getStartingDate());
         }
         if (user.getAddresss() != null) {
             n.setAddresss(user.getAddresss());
         }
         if (user.getSubDistrcit() != null) {
             n.setSubDistrcit(user.getSubDistrcit());
         }
         if (user.getDistrict() != null) {
             n.setDistrict(user.getDistrict());
         }
         if (user.getProvince() != null) {
             n.setProvince(user.getProvince());
         }
         if (user.getPostalCode() != null) {
             n.setPostalCode(user.getPostalCode());
         }
         if (user.getFacebook() != null) {
             n.setFacebook(user.getFacebook());
         }
         if (user.getLineId() != null) {
             n.setLineId(user.getLineId());
         }
         if (user.getInstagram() != null) {
             n.setInstagram(user.getInstagram());
         }
		
         this.userRepo.save(n);
		    
		return n;
	}
	
	public User update(int id ,User user) {
		
	    Optional<User> optionalUser = this.userRepo.findById(id);
	    User n = optionalUser.get();
		  
        if (user.getProfile() != null) {
            n.setProfile(user.getProfile());
        }
        if (user.getCover() != null) {
            n.setCover(user.getCover());
        }
        if (user.getUsername() != null) {
            n.setUsername(user.getUsername());
        }
        if (user.getName() != null) {
            n.setName(user.getName());
        }
        if (user.getLastname() != null) {
            n.setLastname(user.getLastname());
        }
        if (user.getNickname() != null) {
            n.setNickname(user.getNickname());
        }
        if (user.getPosition() != null) {
            n.setPosition(user.getPosition());
        }
        if (user.getNationality() != null) {
            n.setNationality(user.getNationality());
        }
        if (user.getTel() != null) {
            n.setTel(user.getTel());
        }
        if (user.getStartingDate() != null) {
            n.setStartingDate(user.getStartingDate());
        }
        if (user.getAddresss() != null) {
            n.setAddresss(user.getAddresss());
        }
        if (user.getSubDistrcit() != null) {
            n.setSubDistrcit(user.getSubDistrcit());
        }
        if (user.getDistrict() != null) {
            n.setDistrict(user.getDistrict());
        }
        if (user.getProvince() != null) {
            n.setProvince(user.getProvince());
        }
        if (user.getPostalCode() != null) {
            n.setPostalCode(user.getPostalCode());
        }
        if (user.getFacebook() != null) {
            n.setFacebook(user.getFacebook());
        }
        if (user.getLineId() != null) {
            n.setLineId(user.getLineId());
        }
        if (user.getInstagram() != null) {
            n.setInstagram(user.getInstagram());
        }
		
	    this.userRepo.save(n);
	    
	    return n;
	}
}
