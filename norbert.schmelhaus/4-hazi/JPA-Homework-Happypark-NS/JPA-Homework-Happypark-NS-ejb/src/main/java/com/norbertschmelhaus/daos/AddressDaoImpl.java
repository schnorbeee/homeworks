package com.norbertschmelhaus.daos;

import com.norbertschmelhaus.entitys.Address;
import javax.ejb.Stateless;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@Stateless
public class AddressDaoImpl extends AbstractDao<Address> {

    public AddressDaoImpl() {
        super(Address.class);
    }

}
