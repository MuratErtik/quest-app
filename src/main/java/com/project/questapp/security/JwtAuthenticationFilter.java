package com.project.questapp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.questapp.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // frontdan backe istek gelirken default 6 filtreleme vardir spring tarafindan
    // simdi bir tane de biz ekliyoruz

    /**
     * Filtre Zinciri (Filter Chain) Başlar
     * • Spring Security’nin en önemli özelliği, bir “Filtre Zinciri” (Filter Chain)
     * kullanmasıdır.
     * • İstek, backend’e ulaşmadan önce bu zincirdeki filtrelerden geçirilir.
     * 
     */

    /*
     * filtreleme islemleri su sekildedir;
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 1.⁠ ⁠Security Context Creation (Güvenlik Bağlamı Oluşturma)
     * • Her istek için bir “Security Context” oluşturulur. Bu bağlam, kimlik
     * bilgilerini (Authentication) ve yetkileri (Authorities) taşır.
     * • Eğer bir kullanıcı daha önce giriş yapmışsa, bu bilgiler bağlamda saklanmış
     * olabilir.
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 
     * 2. Authentication (Kimlik Doğrulama)
     * • İlk olarak Spring Security, bu isteğin bir kullanıcıdan mı geldiğini
     * kontrol eder.
     * • Eğer istek, bir kullanıcı adı ve şifre içeriyorsa, bunlar doğrulanır.
     * • Eğer bir token (örneğin JWT) gönderildiyse, bu token kontrol edilir.
     * • Eğer doğruysa, Spring Security bu kullanıcıyı “kimlik doğrulandı” olarak
     * işaretler.
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 
     * 3. ⁠Authorization (Yetkilendirme)
     * • Kullanıcının belirli bir kaynağa veya işleve erişim yetkisinin olup
     * olmadığı kontrol edilir. Bu aşamada, kullanıcıya atanmış roller veya izinler
     * devreye girer.
     * • Örneğin:/admin endpoint’ine sadece ROLE_ADMIN yetkisine sahip kullanıcılar
     * erişebilir.
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 
     * 4. CORS Kontrolleri
     * • Eğer istek farklı bir domain’den (örneğin Frontend başka bir sunucuda
     * çalışıyorsa) geliyorsa, bu kontrol yapılır.
     * • Spring Security, CORS ayarlarını kontrol eder ve isteğe izin verilip
     * verilmeyeceğini belirler.
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 
     * 5.⁠ ⁠CSRF Kontrolleri
     * • Spring Security, sahte isteklerin gönderilmesini önlemek için CSRF
     * korumasını sağlar.
     * • Özellikle POST, PUT, DELETE gibi yöntemler kullanıldığında, bir CSRF token
     * beklenir.
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------
     * 
     * 6.⁠ ⁠Exception Handling (Hata Yönetimi)
     * • Eğer yukarıdaki kontrollerden biri başarısız olursa, Spring Security bu
     * durumu yakalar ve bir hata yanıtı döner (örneğin, 401 Unauthorized veya 403
     * Forbidden).
     */

    @Autowired
    /*
     * Bir sınıfın ihtiyaç duyduğu diğer sınıf veya bileşenlerin, sınıf içine elle oluşturulmadan, Spring tarafından sağlanmasıdır.
        Bu, sınıflar arasındaki bağımlılığı gevşetir ve test edilebilirliği artırır.
        Spring, uygulama başlarken, @Autowired ile işaretlenmiş alan, kurucu metod veya setter metodlarını tarar.
        Bu işaretlenmiş yerlere, uygun bir Spring bileşenini (bean) otomatik olarak enjekte eder.
     */
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if (org.springframework.util.StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                //token dolu ve valid ise user i al 
                Long id= jwtTokenProvider.getuserIdFromJwt(jwtToken);
                UserDetails user = userDetailsServiceImpl.loadUserbyId(id);
                //user elimizde artik
                if (user != null) {
                    //requesti authenticate edilebilir simdi.
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //user icin gerekli security bilgilerini tutar
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    //authenticate edildi

                }

            }
        } catch (Exception e) {
            return;
        }
        filterChain.doFilter(request, response);
        //normal olan akis

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (org.springframework.util.StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring("Bearer".length() + 1);
        }
        return null;
        // bunu ayirip sadece tokena ulasmak icin kullanilir
    }

}
