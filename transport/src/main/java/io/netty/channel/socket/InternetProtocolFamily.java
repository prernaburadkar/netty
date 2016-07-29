/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel.socket;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * Internet Protocol (IP) families used byte the {@link DatagramChannel}
 */
public enum InternetProtocolFamily {
    IPv4(Inet4Address.class),
    IPv6(Inet6Address.class);

    private final Class<? extends InetAddress> addressType;
    private final int addressNumber;

    InternetProtocolFamily(Class<? extends InetAddress> addressType) {
        this.addressType = addressType;
        addressNumber = addressNumber(addressType);
    }

    /**
     * Returns the address type of this protocol family.
     */
    public Class<? extends InetAddress> addressType() {
        return addressType;
    }

    /**
     * Returns the <a href="http://www.iana.org/assignments/address-family-numbers/address-family-numbers.xhtml">
     *     address number</a> of the family.
     */
    public int addressNumber() {
        return addressNumber;
    }

    private static int addressNumber(Class<? extends InetAddress> addressType) {
        if (addressType.isAssignableFrom(Inet4Address.class)) {
            return 1;
        } else if (addressType.isAssignableFrom(Inet6Address.class)) {
            return 2;
        } else {
            throw new IllegalArgumentException("addressType " + addressType + " not supported");
        }
    }

    /**
     * Returns the {@link InternetProtocolFamily} for the given {@link InetAddress}.
     */
    public static InternetProtocolFamily familyOf(InetAddress address) {
        if (address instanceof Inet4Address) {
            return IPv4;
        }
        if (address instanceof Inet6Address) {
            return IPv6;
        }
        throw new IllegalArgumentException("address " + address + " not supported");
    }
}
