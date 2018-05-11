package org.tron.core.config;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.tron.common.utils.Base58;

public class Witness implements Serializable {

  private static final long serialVersionUID = -7446501098542377380L;

  @Getter
  private byte[] address;

  @Getter
  private String url;

  @Getter
  @Setter
  private long voteCount;

  /**
   * set address.
   */
  public void setAddress(final byte[] address) {
    if ( !Base58.addressValid(address)){
      throw new IllegalArgumentException(
          "The address(" + address + ") must be a 21 bytes.");
    }
    this.address = address;
  }

  /**
   * set url.
   */
  public void setUrl(final String url) {
    if (StringUtils.isBlank(url)) {
      throw new IllegalArgumentException(
          "The url(" + url + ") format error.");
    }

    this.url = url;
  }
}
