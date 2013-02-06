/*
 * Marge, Java Bluetooth Framework
 * Copyright (C) 2006  Project Marge
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * owner@marge.dev.java.net
 * http://marge.dev.java.net
 */

package bluetooth;

// XXX Temporary before the UUID and Server Name generator.
/**
 * Interface with some default values used by the framework, if you do not
 * specify things in <code>Configuration</code>. It has the deafult UUID,
 * server name and service.
 */
public interface MargeDefaults {

	public static final String DEFAULT_UUID = "102030405060708090A0B0C0D0E0F010";

	public static final String DEFAULT_SERVER_NAME = "BTMargeDefaultServer";

	public static final int DEFAULT_SERVICE = 0x400000;
}
