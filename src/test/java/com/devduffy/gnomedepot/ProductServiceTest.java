package com.devduffy.gnomedepot;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.devduffy.gnomedepot.entity.Product;

import com.devduffy.gnomedepot.repository.ProductRepository;

import com.devduffy.gnomedepot.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    public void getByNameContainingFromRepoTest() {
        when(productRepository.findByNameIgnoreCaseContaining("Evil")).thenReturn(Arrays.asList(
            new Product(4, "Sunnydaze Three Wise Garden Gnomes Hear, Speak, See No Evil Indoor/Outdoor Lawn  Statue Set - 12\" H - 3-Piece Set", "lawn and garden", "https://target.scene7.com/is/image/Target/GUEST_aafedac7-f854-4ae3-85d1-0646af6e68a6?qlt=85&fmt=webp&hei=253&wid=253", "This adorable set  of 3 cheeky gnomes is full of color and personality. These 3 figurines, Seth, Henry, and Steven,  are otherwise known as the \"3 wise gnomes.\" All 3 are recognized famously around their gnome village  for their gestures that represent the common proverb \"hear no evil, speak no evil, see no evil.\"  Their mischievous expressions and playful demeanor imply that they're up to no good, but the truth  is, they're mostly just excited to greet and welcome house guests! With their spunky personalities  and cheerful faces, these gnomes are sure to add charm and laughter to any indoor or outdoor space.  Place them together for a cohesive look or place separately for fun all around. These gnomes would  be a stunning feature on any patio, deck, garden space, yard, or other landscaping area in need of  a charming lift. Their joyful demeanor is sure to complement any indoor or outdoor area.", 5.0, 109, 4, 63.49)
        ));

        List<Product> result = productServiceImpl.getByNameContaining("Evil");

        assertEquals("Sunnydaze Three Wise Garden Gnomes Hear, Speak, See No Evil Indoor/Outdoor Lawn  Statue Set - 12\" H - 3-Piece Set", result.get(0).getName());

        assertEquals("lawn and garden", result.get(0).getCategory());
    }
}
