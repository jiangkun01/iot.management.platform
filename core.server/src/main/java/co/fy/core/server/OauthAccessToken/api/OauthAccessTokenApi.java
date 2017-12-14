package co.fy.core.server.OauthAccessToken.api;


import co.fy.core.server.OauthAccessToken.model.OauthAccessToken;

import java.util.Optional;

/**
 * CREATED IN  2017-12-05 上午9:57
 *
 * @AUTHOR: 姜坤
 **/
public interface OauthAccessTokenApi {
    /**
     * 根据token id 获得用户信息
     * @param tokenId
     * @return
     */
    public Optional<OauthAccessToken> getOneOauthAccessToken(String tokenId);
}
