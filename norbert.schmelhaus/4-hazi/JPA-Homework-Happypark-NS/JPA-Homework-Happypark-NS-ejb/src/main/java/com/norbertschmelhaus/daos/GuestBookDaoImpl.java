package com.norbertschmelhaus.daos;

import com.norbertschmelhaus.entitys.Guest;
import com.norbertschmelhaus.entitys.GuestBook;
import com.norbertschmelhaus.entitys.Happypark;
import com.norbertschmelhaus.exceptions.BadRequestException;
import com.norbertschmelhaus.exceptions.GuestIsntActiveInThisParkException;
import com.norbertschmelhaus.id.GuestBookId;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class GuestBookDaoImpl extends AbstractDao<GuestBook> {

    public GuestBookDaoImpl() {
        super(GuestBook.class);
    }

    public List<GuestBook> getlistOfOneGuestOneParkGuestBookDesc(Long guestPK, Long parkPK) {
        Guest guest = em.find(Guest.class, guestPK);
        Happypark park = em.find(Happypark.class, parkPK);
        if (null != guest && null != park) {
            return em.createNamedQuery("Registration_of_guest_in_happypark", GuestBook.class).setParameter("guest_pk", guest).setParameter("park_pk", park).getResultList();
        }
        throw new BadRequestException("In guest book we haven't any description with this primary key.");
    }

    public GuestBook writeSomethingInGuestBook(GuestBookId pk, String desc) {
        Guest guest = em.find(Guest.class, pk.getGuestFK().getId());
        Happypark park = em.find(Happypark.class, pk.getParkFK().getId());
        if (null != guest && null != park) {
            for (Guest g : park.getActiveGuests()) {
                if (guest.equals(g)) {
                    GuestBook register = new GuestBook(pk, new Date(), desc);
                    return create(register);
                }
            }
            throw new GuestIsntActiveInThisParkException("This guest isn't aktiv guest in this happypark.");
        }
        throw new BadRequestException("Guest or Park id isn't valid.");
    }

}
