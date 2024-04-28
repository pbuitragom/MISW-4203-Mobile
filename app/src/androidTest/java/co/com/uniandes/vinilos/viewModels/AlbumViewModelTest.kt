package co.com.uniandes.vinilos.viewModels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import co.com.uniandes.vinilos.album.model.Album
import co.com.uniandes.vinilos.album.repository.AlbumRepository
import co.com.uniandes.vinilos.album.repository.MockAlbumRepositoryImpl
import co.com.uniandes.vinilos.album.view.AlbumViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyList
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class AlbumViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    //val context = ApplicationProvider.getApplicationContext<Application>()
    //private val repository = mock(AlbumRepository::class.java)
    //private val viewModel = AlbumViewModel(context)

    private lateinit var viewModel: AlbumViewModel
    var fakeRepository: AlbumRepository = MockAlbumRepositoryImpl(ApplicationProvider.getApplicationContext<Application>())

    @Before
    fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            AlbumViewModel::class.java)
    }

    @Test
    fun loadAlbums_loadsCorrectly() {
        val observer = mock(Observer::class.java) as Observer<List<Album>>
        viewModel.albums.observeForever(observer)

        viewModel.albums

        // Verificar que los datos se cargan y notifican a los observadores
        verify(observer).onChanged(anyList())
    }
}
