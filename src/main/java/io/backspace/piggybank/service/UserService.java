package io.backspace.piggybank.service;

//@Service
public class UserService {
    /*@Autowired
    private UserRepository userRepository;

    public BankUsers saveUser(BankUsers bankUsers) {
        List<BankUsers> registeredUsers = userRepository.findByEmail(bankUsers.getEmail());
        if(!registeredUsers.isEmpty())
            throw new IllegalArgumentException("User is registered already");
        return userRepository.save(bankUsers);
    }*/

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<BankUsers> registeredUsers = userRepository.findByEmail(username);
        User user;
        if(registeredUsers.isEmpty())
            throw new UsernameNotFoundException(String.format("No email %s registered with us",username));
        else {
            BankUsers bankUsers = registeredUsers.get(0);
            user = new User(bankUsers.getEmail(),
                    bankUsers.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(bankUsers.getRole())));
        }
        return user;
    }*/
}
