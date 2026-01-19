import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private users: Array<User> = [];
  public signedIn: boolean = false;
  public activeUser: User|null = null;

  constructor(private router: Router) {
    //testni podaci
    this.users.push(new User("Ime 1", "Prezime 1", "user1", "pass1"));
    this.users.push(new User("Ime 2", "Prezime 2", "user2", "pass2"));
    this.users.push(new User("Ime 3", "Prezime 3", "user3", "pass3"));
  }

  public login(username: string, password: string): boolean {
    let result = false;
    this.activeUser = null;
    this.users.forEach(user => {
      if (user.username == username && user.password == password) {
        result = true;
        this.activeUser = user;
      }
    });
    this.signedIn = result;
    return result;
  }

  public logout(){
    this.activeUser = null;
    this.signedIn = false;
    this.router.navigate(['/']);
  }
}
