package co.fy.core.server.OauthAccessToken.impl;

import co.fy.core.server.OauthAccessToken.api.OauthAccessTokenApi;
import co.fy.core.server.OauthAccessToken.dao.OauthAccessTokenMapper;

import co.fy.core.server.OauthAccessToken.model.OauthAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CREATED IN  2017-12-05 上午10:03
 *
 * @AUTHOR: 姜坤
 **/
@Service
public class OauthAccessTokenImpl  implements OauthAccessTokenApi{
    @Autowired
    private OauthAccessTokenMapper oauthAccessTokenMapper;
    @Override
    public Optional<OauthAccessToken> getOneOauthAccessToken(String tokenId) {
        Optional<OauthAccessToken> oauthAccessTokenOptional=Optional.of(oauthAccessTokenMapper.selectByPrimaryKeyToken(tokenId));
        return oauthAccessTokenOptional;
    }
}
