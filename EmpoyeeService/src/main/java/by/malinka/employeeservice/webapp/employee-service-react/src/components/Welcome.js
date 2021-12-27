import React from 'react';

class Welcome extends React.Component {
    render() {
        return (
            <div className="bg-dark text-white">
                <h1>Welcome to Employee Service</h1>
                <blockquote className="blockquote mb-0">
                    <p>
                        Good friends, good books, and a sleepy conscience: this is the ideal life.
                    </p>
                    <footer className="blockquote-footer">
                        Mark Twain
                    </footer>
                </blockquote>
            </div>
        );
    }
}

export default Welcome;