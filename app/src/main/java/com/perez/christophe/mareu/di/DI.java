package com.perez.christophe.mareu.di;

import com.perez.christophe.mareu.repository.MeetingRepository;
import com.perez.christophe.mareu.repository.MeetingRepositoryImpl;

/**
 * Created by Christophe on 03/09/2021.
 *
 * Dependence injection to get instance of service
 */
public class DI {

    private static final MeetingRepository repository = new MeetingRepositoryImpl();

    /**
     * Get an instance of @{@link MeetingRepository}
     *
     * @return
     */
    public static MeetingRepository getMeetingRepository() {
        return repository;
    }


    /**
     * Get always a new uinstance on @{@link MeetingRepository}.Userful for tests, so we context is clean
     * @return
     */
    public static MeetingRepository getNewInstanceMeetingRepository(){
        return new MeetingRepositoryImpl();
    }
}
