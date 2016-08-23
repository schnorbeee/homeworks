package com.norbertschmelhaus.daos;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.entitys.Machine;
import com.norbertschmelhaus.exceptions.BadRequestException;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class MachineDaoImpl extends AbstractDao<Machine> {

    public MachineDaoImpl() {
        super(Machine.class);
    }

    public List<Guest> getlistGuestWhoUseMachine(Long id) {
        Machine machine = read(id);
        if (null != machine) {
            return em.createNamedQuery("List_of_guest_who_use_one_machine", Guest.class).setParameter("machine_pk", id).getResultList();
        }
        throw new BadRequestException("This machine don't exist.");
    }

    public List<Happypark> getlistOfparkWhoUseThisMachine(Long id) {
        Machine machine = read(id);
        if (null != machine) {
            return em.createNamedQuery("List_of_happypark_who_use_this_machine", Happypark.class).setParameter("machine_pk", id).getResultList();
        }
        throw new BadRequestException("This machine don't exist.");
    }

}
