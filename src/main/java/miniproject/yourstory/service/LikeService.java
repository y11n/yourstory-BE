package miniproject.yourstory.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import miniproject.yourstory.entity.Book;
import miniproject.yourstory.entity.Likes;
import miniproject.yourstory.entity.Member;
import miniproject.yourstory.repository.BookRepository;
import miniproject.yourstory.repository.LikeRepository;
import miniproject.yourstory.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LikeService(LikeRepository likeRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.likeRepository = likeRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public int insert(long book_id, String username) throws Exception {
        Likes like = likeRepository.findByBookIdAndMemberUsername(book_id, username);
        if(like != null){
            throw new Exception("이미 좋아요한 책입니다.");
        }
        Book book = bookRepository.findById(book_id);
        Member member = memberRepository.findByUsername(username);
        Likes likes = new Likes();
        likes.setBook(book);
        likes.setMember(member);
        likeRepository.save(likes);
        return book.getLikesCount();
    }

    @Transactional
    public int delete(long book_id, String username) throws Exception{
        Likes like = likeRepository.findByBookIdAndMemberUsername(book_id, username);
        if(like == null){
            throw new Exception("좋아요 하지 않은 책입니다");
        }

        likeRepository.delete(like);

        Book book = bookRepository.findById(book_id);
        return book.getLikesCount();
    }
}
