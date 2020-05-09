package captcha.service.impl;

import captcha.mapper.CaptchaMapper;
import captcha.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 22510
 */
@Service
@Transactional
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    CaptchaMapper captchaMapper;
    @Override
    public boolean isTelPresence(String tel) {
        return captchaMapper.isTelPresence(tel) == null;
    }
}
