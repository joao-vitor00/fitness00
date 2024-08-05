package com.jvsc.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvsc.Dao.DaoImpl.UserJdbc;
import com.jvsc.Entity.Usuario;
import com.jvsc.Request.user.AddUserDto;
import com.jvsc.Request.user.DeleteUserDto;
import com.jvsc.Request.user.UpdateUserDto;
import com.jvsc.Response.Dto.UserDto;
import com.jvsc.Response.ErroResponse;
import com.jvsc.Response.FitException;
import com.jvsc.utils.Utils;

@Service
public class UserService {

    @Autowired
    private UserJdbc userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto findUser(long id) {
        Usuario foundUser = userDao.findUser(id);
        if (foundUser == null) {
            throw new FitException("Usuário não encontrado", HttpStatus.NOT_FOUND.value());
        }
        return new UserDto( foundUser.getName(), foundUser.getEmail(), foundUser.getBirthDate(), foundUser.getId());
    }

    public void deleteUser(DeleteUserDto user) {
        if (userDao.findUser(user.getId()) == null) {
            throw new FitException("Usuário não encontrado", HttpStatus.NOT_FOUND.value());
        }
        userDao.deleteUser(user);
    }

    public void addUser(AddUserDto user) {
        if (validateAddUser(new AddUserDto( user.getName(), user.getEmail(),"", user.getBirthDate() ))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.addUser(user);
        }
        
    }
    public List<UserDto> listUsers() {
        List<UserDto> users = userDao.listUsers();
        
        
        if (users == null || users.isEmpty()) {
            throw new FitException("Nenhum usuário encontrado", HttpStatus.NOT_FOUND.value());
        }
        return users;
    }

    public UserDto updateUser(UpdateUserDto user) {
        if (userDao.findUser(user.getId()) == null) {
            throw new FitException("Usuário não encontrado", HttpStatus.NOT_FOUND.value());
        }
        var date = new Date();
        try {
        date = Utils.convertToDate(user.getBirthDate());
        } catch (Exception e) {
            throw new FitException("Erro interno ao validar as informações", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        if (validateUpdateUser(new UserDto(user.getName(), user.getEmail(), date, user.getId()))) {
            
            user.setNewPassword(passwordEncoder.encode(user.getNewPassword()));
            var updatedUser = userDao.updateUser(user);
            
            return new UserDto(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getBirthDate(), updatedUser.getId());
        }
        return null;
    }

    public boolean validateAddUser(AddUserDto user) {
        ArrayList<ErroResponse> errors = new ArrayList<>();
        Date date = new Date();
        var foundUser = userDao.findEmail(user.getEmail());
        if (foundUser != null) {
            errors.add(new ErroResponse("Email ja utilizado", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.add(new ErroResponse("Email obrigatório", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getName() == null || user.getName().isEmpty()) {
            errors.add(new ErroResponse("Nome obrigatório", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getBirthDate() == null || user.getBirthDate().isEmpty()) {
            errors.add(new ErroResponse("Data de nascimento obrigatória", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidEmail(user.getEmail()) == false) {
            errors.add(new ErroResponse("Email inválido", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidDate(user.getBirthDate()) == false) {
            errors.add(new ErroResponse("Data de nascimento inválido", HttpStatus.BAD_REQUEST.value()));
        }else{ 
            
            if(Utils.isOfLegalAge(user.getBirthDate()) == false) {
                errors.add(new ErroResponse("Idade inválida", HttpStatus.BAD_REQUEST.value()));
            }
        }
        if(Utils.isValidName(user.getName()) == false) {
            errors.add(new ErroResponse("Nome inválido", HttpStatus.BAD_REQUEST.value()));
        }
        if(!errors.isEmpty()) {
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }
        return true;
    }

    

    public boolean validateUpdateUser(UserDto user) {
        ArrayList<ErroResponse> errors = new ArrayList<>();
        Date date = new Date();
        var foundUser = userDao.findEmail(user.getEmail());
        if (foundUser != null && foundUser.getId() != user.getId()) {
            errors.add(new ErroResponse("Email ja utilizado", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.add(new ErroResponse("Email obrigatório", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getName() == null || user.getName().isEmpty()) {
            errors.add(new ErroResponse("Nome obrigatório", HttpStatus.BAD_REQUEST.value()));
        }
        if(user.getBirthDate() == null){
            errors.add(new ErroResponse("Data de nascimento obrigatória", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidEmail(user.getEmail()) == false) {
            errors.add(new ErroResponse("Email inválido", HttpStatus.BAD_REQUEST.value()));
        }
        if(Utils.isValidDate(Utils.convertDateToString(user.getBirthDate())) == false) {
            errors.add(new ErroResponse("Data de nascimento inválido", HttpStatus.BAD_REQUEST.value()));
        }else{ 
            
            if(Utils.isOfLegalAge(Utils.convertDateToString(user.getBirthDate())) == false) {
                errors.add(new ErroResponse("Idade inválida", HttpStatus.BAD_REQUEST.value()));
            }
        }
        if(Utils.isValidName(user.getName()) == false) {
            errors.add(new ErroResponse("Nome inválido", HttpStatus.BAD_REQUEST.value()));
        }
        if(!errors.isEmpty()) {
            throw new FitException(errors, HttpStatus.BAD_REQUEST.value());
        }
        return true;
    }

    
}
