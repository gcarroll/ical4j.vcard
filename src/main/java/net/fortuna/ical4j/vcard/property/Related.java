/**
 * Copyright (c) 2009, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.vcard.property;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.vcard.Parameter;
import net.fortuna.ical4j.vcard.Property;
import net.fortuna.ical4j.vcard.parameter.Type;

/**
 * $Id$
 *
 * Created on 21/09/2008
 *
 * @author Ben
 *
 */
public final class Related extends Property {

    /**
     * 
     */
    private static final long serialVersionUID = -3319959600372278036L;
    
    private URI uri;
    
    /**
     * @param value
     * @param types
     * @throws URISyntaxException
     */
    public Related(String value, Type...types) throws URISyntaxException {
    	this(new URI(value), types);
    }
    
    /**
     * @param uri
     * @param types
     */
    public Related(URI uri, Type...types) {
        super(Id.RELATED);
        this.uri = uri;
        getParameters().addAll(Arrays.asList(types));
    }
    
    /**
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /* (non-Javadoc)
     * @see net.fortuna.ical4j.vcard.Property#getValue()
     */
    @Override
    public String getValue() {
        return Strings.valueOf(uri);
    }

    /* (non-Javadoc)
     * @see net.fortuna.ical4j.vcard.Property#validate()
     */
    @Override
    public void validate() throws ValidationException {
        for (Parameter param : getParameters()) {
            try {
                assertTypeParameter(param);
            }
            catch (ValidationException ve) {
                assertPidParameter(param);
            }
        }
    }

}
