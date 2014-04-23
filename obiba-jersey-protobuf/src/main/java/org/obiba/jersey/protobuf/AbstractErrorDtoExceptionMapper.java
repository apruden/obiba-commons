/*
 * Copyright (c) 2014 OBiBa. All rights reserved.
 *
 * This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.obiba.jersey.protobuf;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessage;

public abstract class AbstractErrorDtoExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

  private static final Logger log = LoggerFactory.getLogger(AbstractErrorDtoExceptionMapper.class);

  protected abstract Response.Status getStatus();

  protected abstract GeneratedMessage.ExtendableMessage<?> getErrorDto(E exception);

  @Override
  public Response toResponse(E exception) {
    log.debug("Exception", exception);
    return Response.status(getStatus()).type("application/x-protobuf+json").entity(getErrorDto(exception)).build();
  }

}
